document.querySelector("input[type=file]").addEventListener("change", function () {
    let fileInput = document.querySelector("input[name=uploadFile]");
    let fileObj = fileInput.files[0];

    let formData = new FormData();

    formData.append("uploadfile", fileObj);

    console.log("fileList: " + fileList);
    console.log("fileList[0].name" + fileList[0].name);
    console.log("fileList[0].size" + fileList[0].size);
    console.log("fileList[0].type" + fileList[0].type);

    $.ajax({
        url: '/uploadTest',
        processData : false,
        contentType : false,
        data : formData,
        type : 'POST',
        dataType : 'json'
    });
});