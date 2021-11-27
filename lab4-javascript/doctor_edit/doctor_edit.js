import {getGatewayUrl} from "../js/config.js";
import {getParamByName} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateDoctor(event));

    getAndDisplayDoctor();
});

function getAndDisplayDoctor()
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
    httpRequest.open("GET", getGatewayUrl() + "/api/departments/" + getParamByName("department")
        + "/doctors/" + getParamByName("doctor"), true);
    httpRequest.send();
}

function updateDoctor(event)
{
    event.preventDefault();

    const httpRequest = new XMLHttpRequest();
    const okCode = 200;
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === okCode) {
            getAndDisplayDoctor(JSON.parse(this.responseText));
        }
    };
    httpRequest.open("PUT", getGatewayUrl() + "/api/departments/" + getParamByName("department")
        + "/doctors/" + getParamByName("doctor"), true);

    const request =
        {
            "name": document.getElementById("name").value,
            "surname": document.getElementById("surname").value
        }

    httpRequest.setRequestHeader("Content-Type", "application/json");

    httpRequest.send(JSON.stringify(request));

    window.location.href = '../department_view/department_view.html?department=' + getParamByName('department');
}