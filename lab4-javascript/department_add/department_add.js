import {getGatewayUrl} from "../js/config.js";

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => addDepartment(event));
});

function addDepartment(event)
{
    event.preventDefault();

    const httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", getGatewayUrl() + "/api/departments", true);

    const request =
        {
            "name": document.getElementById("name").value,
            "numberOfBeds": document.getElementById("numberOfBeds").value,
            "numberOfRooms": document.getElementById("numberOfRooms").value
        }

    httpRequest.setRequestHeader("Content-Type", "application/json");

    httpRequest.send(JSON.stringify(request));

    window.location.href = '../department_list/department_list.html';
}