export function deleteElementChildren(element)
{
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

export function createTextCell(text)
{
    const dataElement = document.createElement("td");
    dataElement.appendChild(document.createTextNode(text));
    return dataElement;
}

export function createLinkCell(text, url)
{
    const dataElement = document.createElement("td");
    const a = document.createElement("a");
    a.appendChild(document.createTextNode(text));
    a.href = url;
    dataElement.appendChild(a);
    return dataElement;
}

export function createButtonCell(text, action)
{
    const dataElement = document.createElement("td");
    const button = document.createElement("button");
    button.appendChild(document.createTextNode(text));
    button.addEventListener("click", action);
    dataElement.appendChild(button);
    return dataElement;
}

export function setTextNode(id, text) {
    let element = document.getElementById(id);
    deleteElementChildren(element);
    element.appendChild(document.createTextNode(text));
}

export function getParamByName(name) {
    return new URLSearchParams(window.location.search).get(name);
}