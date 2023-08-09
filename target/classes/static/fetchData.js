let hostname = window.location.hostname;
let port = 9091;
let url = "http://" + hostname + ":" + port + "/api/boards/cards"

fetch(url)
    .then((response) => response.json())
    .then((parsedResponse) => {
        console.log(parsedResponse);

        parsedResponse.forEach(card => {
            if (card.section === 1) {
                let todo = document.getElementById("todo");
                let newListItem = document.createElement("li");
                newListItem.textContent = card.id + " -- " + card.title + " -- " + card.description;
                todo.appendChild(newListItem);
            } else if (card.section === 2) {
                let inprogress = document.getElementById("inprogress");
                let newListItem = document.createElement("li");
                newListItem.textContent = card.id + " -- " + card.title + " -- " + card.description;
                inprogress.appendChild(newListItem);
            } else if (card.section === 3) {
                let done = document.getElementById("done");
                let newListItem = document.createElement("li");
                newListItem.textContent = card.id + " -- " + card.title + " -- " + card.description;
                done.appendChild(newListItem);
            }
        });
    })
    .catch(error => {
        console.error('Error fetching card info:', error);
    });






document.addEventListener('DOMContentLoaded', function () {
    const addCardBtn = document.getElementById('add-card-btn');
    addCardBtn.addEventListener('click', addCard);
});

async function addCard() {
    const cardTitle = document.getElementById('card-title').value;
    const cardDescription = document.getElementById('card-description').value;
    const cardSection = document.getElementById('card-section').value;

    if (!cardTitle || !cardDescription || !cardSection) {
        alert('Please enter a title, description, and select a section for the card.');
        return;
    }

    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    const raw = JSON.stringify({
        title: cardTitle,
        description: cardDescription,
        section: parseInt(cardSection)
    });

    const requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    try {
        const response = await fetch('http://localhost:8080/api/boards/1/cards', requestOptions);
        if (response.ok) {
            const result = await response.text();
            alert('Card added successfully!');
            // Optionally, you can update the cards list or reload the page to display the new card.
            window.location.reload();
        } else {
            console.error('Failed to add the card.');
            // Handle error if needed.
        }
    } catch (error) {
        console.error('Error adding the card:', error);
        // Handle error if needed.
    }
}
document.addEventListener('DOMContentLoaded', function () {
    const deleteCardBtn = document.getElementById('delete-card-btn');
    deleteCardBtn.addEventListener('click', deleteCard);
});

async function deleteCard() {
    const cardIdToDelete = document.getElementById('card-delete-id').value;

    if (!cardIdToDelete) {
        alert('Please enter the ID of the card you want to delete.');
        return;
    }

    const requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };

    try {
        const response = await fetch(`http://localhost:8080/api/boards/1/cards/${cardIdToDelete}`, requestOptions);
        if (response.ok) {
            const result = await response.text();
            console.log('Card deleted successfully:', result);
             alert('Card deleted successfully!');
             window.location.reload();
            // Optionally, you can update the cards list or reload the page to reflect the deletion.
            // Remove the deleted card from the UI if needed.
        } else {
            console.error('Failed to delete the card.');
            // Handle error if needed.
        }
    } catch (error) {
        console.error('Error deleting the card:', error);
        // Handle error if needed.
    }
}
document.addEventListener('DOMContentLoaded', function () {
    const editCardBtn = document.getElementById('edit-card-btn');
    editCardBtn.addEventListener('click', editCard);
});

async function editCard() {
    const cardIdToEdit = document.getElementById('card-edit-id').value;
    const cardTitle = document.getElementById('card-edit-title').value;
    const cardDescription = document.getElementById('card-edit-description').value;
    const cardSection = document.getElementById('card-edit-section').value;

    if (!cardIdToEdit || !cardTitle || !cardDescription || !cardSection) {
        alert('Please enter card ID, title, description, and select a section.');
        return;
    }

    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    const raw = JSON.stringify({
        title: cardTitle,
        description: cardDescription,
        section: parseInt(cardSection)
    });

    const requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    try {
        const response = await fetch(`http://localhost:8080/api/boards/1/cards/${cardIdToEdit}`, requestOptions);
        if (response.ok) {
            const result = await response.text();
            console.log('Card edited successfully:', result);
            alert('Card edited successfully!');
                         window.location.reload();
            // Optionally, you can update the cards list or reload the page to reflect the changes.
            // Update the UI with the edited card details if needed.
        } else {
            console.error('Failed to edit the card.');
            // Handle error if needed.
        }
    } catch (error) {
        console.error('Error editing the card:', error);
        // Handle error if needed.
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const updateBoardTitleBtn = document.getElementById('update-board-title-btn');
    updateBoardTitleBtn.addEventListener('click', updateBoardTitle);
});

async function updateBoardTitle() {
    const newBoardTitle = document.getElementById('new-board-title').value;

    if (!newBoardTitle) {
        alert('Please enter a new board title.');
        return;
    }

    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    const raw = JSON.stringify({
        title: newBoardTitle
    });

    const requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    try {
        const response = await fetch('http://localhost:8080/api/boards/1', requestOptions);
        if (response.ok) {
            const result = await response.text();
            console.log('Board title updated successfully:', result);

            // Update the UI to display the new board title
            const boardTitleElement = document.getElementById('board-title');
            boardTitleElement.textContent = newBoardTitle;
        } else {
            console.error('Failed to update the board title.');
            // Handle error if needed.
        }
    } catch (error) {
        console.error('Error updating the board title:', error);
        // Handle error if needed.
    }
}






