import {getGatewayUrl} from "../js/config.js";
import {getParamByName} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateDepartment(event));

    getAndDisplayDepartment();
});

function getAndDisplayDepartment()
{
    const httpRequest = new XMLHttpRequest();
    const okCode = 200;
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === okCode) {
            let response = (JSON.parse(this.responseText));
            for (const [key, value] of Object.entries(response))
            {
                let input = document.getElementById(key);
                if(input)
                {
                    input.value = value;
                }
            }
        }
    };
    httpRequest.open("GET", getGatewayUrl() + "/api/departments/" + getParamByName("department"),true);
    httpRequest.send();
}

function updateDepartment(event)
{
    event.preventDefault();

    const httpRequest = new XMLHttpRequest();
    const okCode = 200;
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === okCode) {
            getAndDisplayDepartment(JSON.parse(this.responseText));
        }
    };
    httpRequest.open("PUT", getGatewayUrl() + "/api/departments/" + getParamByName("department"), true);

    const request =
        {
            "numberOfBeds": document.getElementById("numberOfBeds").value,
            "numberOfRooms": document.getElementById("numberOfRooms").value
        }

    httpRequest.setRequestHeader("Content-Type", "application/json");

    httpRequest.send(JSON.stringify(request));
}