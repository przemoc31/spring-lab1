import {getGatewayUrl} from "../js/config.js";
import {getParamByName} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => addDoctor(event));
});

function addDoctor(event)
{
    event.preventDefault();

    const httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", getGatewayUrl() + "/api/doctors", true);

    const request =
        {
            "name": document.getElementById("name").value,
            "surname": document.getElementById("surname").value,
            "department": getParamByName("department")
        }

    httpRequest.setRequestHeader("Content-Type", "application/json");

    httpRequest.send(JSON.stringify(request));

    window.location.href = '../department_view/department_view.html?department=' + getParamByName('department');
}