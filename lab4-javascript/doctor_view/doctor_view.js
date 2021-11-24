import {getGatewayUrl} from "../js/config.js";
import {getParamByName, setTextNode} from "../js/dom_utils.js";

window.addEventListener('load', () =>
{
    getAndDisplayDoctor();
})

function displayDoctor(doctor) {
    setTextNode('doctorName', doctor.name + " " + doctor.surname);
    setTextNode('id', doctor.id);
    setTextNode('name', doctor.name);
    setTextNode('surname', doctor.surname);
    setTextNode('department', doctor.department);
}

function getAndDisplayDoctor()
{
    const httpRequest = new XMLHttpRequest();
    const okCode = 200;
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === okCode)
        {
            displayDoctor(JSON.parse(this.responseText));
        }
    };
    httpRequest.open("GET", getGatewayUrl() + "/api/departments/" + getParamByName("department")
        + "/doctors/" + getParamByName("doctor"), true);
    httpRequest.send();
}