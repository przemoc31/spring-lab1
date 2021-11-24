import {getGatewayUrl} from "../js/config.js";
import {
    createButtonCell,
    createLinkCell,
    createTextCell,
    deleteElementChildren,
    getParamByName,
    setTextNode
} from "../js/dom_utils.js";

window.addEventListener('load', () =>
{
    getAndDisplayDepartment();
    getAndDisplayDoctors();
})

function displayDepartment(department) {
    setTextNode('departmentName', department.name);
    setTextNode('name', department.name);
    setTextNode('numberOfBeds', department.numberOfBeds);
    setTextNode('numberOfRooms', department.numberOfRooms);
}

function getAndDisplayDepartment()
{
    const httpRequest = new XMLHttpRequest();
    const okCode = 200;
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === okCode) {
            displayDepartment(JSON.parse(this.responseText));
        }
    };
    httpRequest.open("GET", getGatewayUrl() + '/api/departments/'
        + getParamByName("department"), true);
    httpRequest.send();
}

function createRowInTable(doctor) {
    let rowElement = document.createElement('tr');
    rowElement.appendChild(createTextCell(doctor.name));
    rowElement.appendChild(createTextCell(doctor.surname));
    rowElement.appendChild(createLinkCell('view', '../doctor_view/doctor_view.html?department='
        + getParamByName('department') + '&doctor=' + doctor.id));
    rowElement.appendChild(createLinkCell('edit', '../doctor_edit/doctor_edit.html?department='
        + getParamByName('department') + '&doctor=' + doctor.id));
    rowElement.appendChild(createButtonCell('delete', () => deleteDoctor(doctor.id)));
    return rowElement;
}

function displayDoctors(doctors)
{
    let tableBody = document.getElementById("tableBody");
    deleteElementChildren(tableBody);
    doctors.doctors.forEach(doctor => { tableBody.appendChild(createRowInTable(doctor)) })
}

function getAndDisplayDoctors() {
    const httpRequest = new XMLHttpRequest();
    const okCode = 200;
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === okCode) {
            displayDoctors(JSON.parse(this.responseText))
        }
    };
    httpRequest.open("GET", getGatewayUrl() + "/api/departments/" + getParamByName("department")
        + "/doctors", true);
    httpRequest.send();
}

function deleteDoctor(doctorId)
{
    const httpRequest = new XMLHttpRequest();
    const acceptedCode = 202;
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === acceptedCode) {
            getAndDisplayDoctors();
        }
    };
    httpRequest.open("DELETE", getGatewayUrl() + "/api/departments/" + getParamByName("department")
        + "/doctors/" + doctorId, true);
    httpRequest.send();
}