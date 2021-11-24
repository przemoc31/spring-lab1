import {getGatewayUrl} from "../js/config.js";
import {deleteElementChildren, createTextCell, createLinkCell, createButtonCell} from "../js/dom_utils.js";

window.addEventListener('load', () => {getAndDisplayDepartments();});

function createRowInTable(department) {
    let rowElement = document.createElement('tr');
    rowElement.appendChild(createTextCell(department));
    rowElement.appendChild(createLinkCell('view', '../department_view/department_view.html?department=' + department));
    rowElement.appendChild(createLinkCell('edit', '../department_edit/department_edit.html?department=' + department));
    rowElement.appendChild(createButtonCell('delete', () => deleteDepartment(department)));
    return rowElement;
}

function displayDepartments(departments)
{
    let tableBody = document.getElementById("tableBody");
    deleteElementChildren(tableBody);
    departments.departments.forEach(department => { tableBody.appendChild(createRowInTable(department.name)) });
}


function getAndDisplayDepartments()
{
    const httpRequest = new XMLHttpRequest();
    const okCode = 200;
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === okCode) {
            displayDepartments(JSON.parse(this.responseText));
        }
    };
    httpRequest.open("GET", getGatewayUrl() + '/api/departments', true);
    httpRequest.send();
}

function deleteDepartment(department) {
    const httpRequest = new XMLHttpRequest();
    const acceptedCode = 202;
    httpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === acceptedCode) {
            getAndDisplayDepartments();
        }
    };
    httpRequest.open("DELETE", getGatewayUrl() + '/api/departments/' + department, true);
    httpRequest.send();
}