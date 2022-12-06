function getDomain(fullUrl) {
    const myArray = fullUrl.split("/");
    let textOut = "";
    for (let i = 0; i < 3; i++) {
        textOut += myArray[i] + "/";
    }
    return textOut;
}