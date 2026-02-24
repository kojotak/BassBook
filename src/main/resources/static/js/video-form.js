// Load initial data
document.addEventListener('DOMContentLoaded', function() {
    loadArtists();
    loadChannels();
    loadInstruments();
    setupEventListeners();
});

function setupEventListeners() {
    // Artist selection change
    document.getElementById('artistSelect').addEventListener('change', function() {
        const artistId = this.value;
        if (artistId) {
            loadSongs(artistId);
            document.getElementById('songSelect').disabled = false;
            document.getElementById('addSongBtn').disabled = false;
        } else {
            document.getElementById('songSelect').disabled = true;
            document.getElementById('addSongBtn').disabled = true;
            document.getElementById('songSelect').innerHTML = '<option value="">-- Select Artist First --</option>';
        }
    });

    // Instrument selection change
    document.getElementById('instrumentSelect').addEventListener('change', function() {
        const instrumentId = this.value;
        console.log('Instrument selected:', instrumentId);
        if (instrumentId) {
            loadTunings(instrumentId);
            document.getElementById('tuningSelect').disabled = false;
        } else {
            document.getElementById('tuningSelect').disabled = true;
            document.getElementById('tuningSelect').innerHTML = '<option value="">-- Select Instrument First --</option>';
        }
    });

    // Modal buttons
    document.getElementById('addArtistBtn').addEventListener('click', () => openModal('artistModal', true));
    document.getElementById('addSongBtn').addEventListener('click', () => openModal('songModal', true));
    document.getElementById('addChannelBtn').addEventListener('click', () => openModal('channelModal', true));
    document.getElementById('addInstrumentBtn').addEventListener('click', () => openModal('instrumentModal'));

    // Save buttons
    document.getElementById('saveArtistBtn').addEventListener('click', saveArtist);
    document.getElementById('saveSongBtn').addEventListener('click', saveSong);
    document.getElementById('saveChannelBtn').addEventListener('click', saveChannel);
    document.getElementById('saveInstrumentBtn').addEventListener('click', saveInstrument);

    // Close buttons
    document.querySelectorAll('.close').forEach(btn => {
        btn.addEventListener('click', function() {
            closeModal(this.getAttribute('data-modal'));
        });
    });

    // Close modal on outside click
    window.addEventListener('click', function(event) {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    });

    // Form submission
    document.getElementById('videoForm').addEventListener('submit', saveVideo);
}

// Load functions
async function loadArtists() {
    try {
        const response = await fetch('/api/artists');
        const artists = await response.json();

        // Sort artists alphabetically by name
        artists.sort((a, b) => a.name.localeCompare(b.name));

        const select = document.getElementById('artistSelect');
        select.innerHTML = '<option value="">-- Select Artist --</option>';
        artists.forEach(artist => {
            const option = document.createElement('option');
            option.value = artist.id;
            option.textContent = artist.name;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading artists:', error);
    }
}

async function loadSongs(artistId) {
    try {
        const response = await fetch(`/api/songs?artistId=${artistId}`);
        const songs = await response.json();

        // Sort songs alphabetically by name
        songs.sort((a, b) => a.name.localeCompare(b.name));

        const select = document.getElementById('songSelect');
        select.innerHTML = '<option value="">-- Select Song --</option>';
        songs.forEach(song => {
            const option = document.createElement('option');
            option.value = song.id;
            option.textContent = song.name;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading songs:', error);
    }
}

async function loadChannels() {
    try {
        const response = await fetch('/api/channels');
        const channels = await response.json();

        // Sort channels alphabetically by name
        channels.sort((a, b) => a.name.localeCompare(b.name));

        const select = document.getElementById('channelSelect');
        select.innerHTML = '<option value="">-- Select Channel --</option>';
        channels.forEach(channel => {
            const option = document.createElement('option');
            option.value = channel.id;
            option.textContent = channel.name;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading channels:', error);
    }
}

async function loadInstruments() {
    try {
        const response = await fetch('/api/instruments');
        const instruments = await response.json();

        // Sort instruments alphabetically by name
        instruments.sort((a, b) => a.name.localeCompare(b.name));

        const select = document.getElementById('instrumentSelect');
        select.innerHTML = '<option value="">-- Select Instrument --</option>';
        instruments.forEach(instrument => {
            const option = document.createElement('option');
            option.value = instrument.id;
            option.textContent = instrument.name;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading instruments:', error);
    }
}

async function loadTunings(instrumentId) {
    try {
        console.log('Loading tunings for instrument:', instrumentId);
        const response = await fetch(`/api/tunings?instrumentId=${instrumentId}`);
        const tunings = await response.json();
        console.log('Tunings received:', tunings);

        const select = document.getElementById('tuningSelect');
        select.innerHTML = '<option value="">-- Select Tuning --</option>';
        tunings.forEach(tuning => {
            const option = document.createElement('option');
            option.value = tuning.id;
            option.textContent = tuning.name;
            select.appendChild(option);
        });

        // Select the first tuning (lowest id) as default
        if (tunings.length > 0) {
            select.value = tunings[0].id;
        }
        console.log('Tunings loaded, select value:', select.value);
    } catch (error) {
        console.error('Error loading tunings:', error);
    }
}

// Helper function to get video title from metadata
function getVideoTitle() {
    const metadataItems = document.querySelectorAll('.metadata-item');
    for (const item of metadataItems) {
        const label = item.querySelector('.metadata-label');
        if (label && label.textContent.includes('Title:')) {
            const valueElement = item.querySelector('.metadata-value');
            return valueElement ? valueElement.textContent.trim() : '';
        }
    }
    return '';
}

// Modal functions
function openModal(modalId, prefillValue = false) {
    const modal = document.getElementById(modalId);

    // Prefill the input with the provided value
    if (prefillValue) {
        if (modalId === 'artistModal') {
            document.getElementById('newArtistName').value = getVideoTitle();
        } else if (modalId === 'songModal') {
            document.getElementById('newSongName').value = getVideoTitle();
        } else if (modalId === 'channelModal') {
            const metadataItems = document.querySelectorAll('.metadata-item');
            for (const item of metadataItems) {
                const label = item.querySelector('.metadata-label');
                if (label && label.textContent.includes('Channel name:')) {
                    const valueElement = item.querySelector('.metadata-value');
                    document.getElementById('newChannelName').value = valueElement ? valueElement.textContent.trim() : '';
                } else if (label && label.textContent.includes('Channel id:')) {
                    const valueElement = item.querySelector('.metadata-value');
                    document.getElementById('newChannelYoutubeId').value  = valueElement ? valueElement.textContent.trim() : '';
                }
            }
        }
    }

    modal.style.display = 'block';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// Save functions
async function saveArtist() {
    const name = document.getElementById('newArtistName').value.trim();
    if (!name) {
        alert('Please enter an artist name');
        return;
    }

    try {
        const response = await fetch('/api/artists', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name })
        });

        if (response.ok) {
            const newArtist = await response.json();
            closeModal('artistModal');
            document.getElementById('newArtistName').value = '';
            await loadArtists();

            // Preselect the newly created artist
            document.getElementById('artistSelect').value = newArtist.id;
            // Trigger change event to enable song selection
            document.getElementById('artistSelect').dispatchEvent(new Event('change'));

            showMessage('Artist added successfully!', 'success');
        } else {
            showMessage('Error adding artist', 'error');
        }
    } catch (error) {
        console.error('Error saving artist:', error);
        showMessage('Error adding artist', 'error');
    }
}

async function saveSong() {
    const name = document.getElementById('newSongName').value.trim();
    const artistId = document.getElementById('artistSelect').value;

    if (!name) {
        alert('Please enter a song name');
        return;
    }

    if (!artistId) {
        alert('Please select an artist first');
        return;
    }

    try {
        const response = await fetch('/api/songs', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, artistId })
        });

        if (response.ok) {
            const newSong = await response.json();
            closeModal('songModal');
            document.getElementById('newSongName').value = '';
            await loadSongs(artistId);

            // Preselect the newly created song
            document.getElementById('songSelect').value = newSong.id;

            showMessage('Song added successfully!', 'success');
        } else {
            showMessage('Error adding song', 'error');
        }
    } catch (error) {
        console.error('Error saving song:', error);
        showMessage('Error adding song', 'error');
    }
}

async function saveChannel() {
    const name = document.getElementById('newChannelName').value.trim();
    const youtubeId = document.getElementById('newChannelYoutubeId').value.trim();

    if (!name || !youtubeId) {
        alert('Please enter both channel name and YouTube ID');
        return;
    }

    try {
        const response = await fetch('/api/channels', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, youtubeId })
        });

        if (response.ok) {
            const newChannel = await response.json();
            closeModal('channelModal');
            document.getElementById('newChannelName').value = '';
            document.getElementById('newChannelYoutubeId').value = '';
            await loadChannels();

            // Preselect the newly created channel
            document.getElementById('channelSelect').value = newChannel.id;

            showMessage('Channel added successfully!', 'success');
        } else {
            showMessage('Error adding channel', 'error');
        }
    } catch (error) {
        console.error('Error saving channel:', error);
        showMessage('Error adding channel', 'error');
    }
}

async function saveInstrument() {
    const name = document.getElementById('newInstrumentName').value.trim();
    if (!name) {
        alert('Please enter an instrument name');
        return;
    }

    try {
        const response = await fetch('/api/instruments', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name })
        });

        if (response.ok) {
            closeModal('instrumentModal');
            document.getElementById('newInstrumentName').value = '';
            await loadInstruments();
            showMessage('Instrument added successfully!', 'success');
        } else {
            showMessage('Error adding instrument', 'error');
        }
    } catch (error) {
        console.error('Error saving instrument:', error);
        showMessage('Error adding instrument', 'error');
    }
}

async function saveVideo(event) {
    event.preventDefault();

    const videoData = {
        youtubeId: document.getElementById('videoId').value.trim(),
        artistId: document.getElementById('artistSelect').value,
        songId: document.getElementById('songSelect').value,
        channelId: document.getElementById('channelSelect').value,
        instrumentId: document.getElementById('instrumentSelect').value,
        tuningId: document.getElementById('tuningSelect').value
    };

    try {
        const response = await fetch('/api/videos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(videoData)
        });

        const result = await response.json();

        if (response.ok) {
            showMessage('Video saved successfully! Redirecting...', 'success');
            setTimeout(() => {
                window.location.href = '/';
            }, 1000);
        } else {
            showMessage('Error: ' + (result.error || 'Failed to save video'), 'error');
        }
    } catch (error) {
        console.error('Error saving video:', error);
        showMessage('Error saving video', 'error');
    }
}

function showMessage(message, type) {
    const messageDiv = document.getElementById('formMessage');
    messageDiv.textContent = message;
    messageDiv.className = 'form-message ' + type;
    
    setTimeout(() => {
        messageDiv.style.display = 'none';
        messageDiv.className = 'form-message';
    }, 5000);
}
