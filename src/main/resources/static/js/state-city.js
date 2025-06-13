// Load all Indian states on page load
window.onload = function () {
    fetch('https://countriesnow.space/api/v0.1/countries/states', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ country: 'India' })
    })
    .then(res => res.json())
    .then(data => {
        const stateSelect = document.getElementById('state');
        data.data.states.forEach(state => {
            const option = document.createElement('option');
            option.value = state.name;
            option.text = state.name;
            stateSelect.appendChild(option);
        });
    });
};

// Fetch cities when a state is selected
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('state').addEventListener('change', function () {
        const stateName = this.value;
        const citySelect = document.getElementById('city');
        citySelect.innerHTML = '<option value="">Loading...</option>';

        fetch('https://countriesnow.space/api/v0.1/countries/state/cities', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ country: 'India', state: stateName })
        })
        .then(res => res.json())
        .then(data => {
            citySelect.innerHTML = '<option value="">Select City</option>';
            data.data.forEach(city => {
                const option = document.createElement('option');
                option.value = city;
                option.text = city;
                citySelect.appendChild(option);
            });
        });
    });
});
