$(document).ready(function () {
    $('#userForm').submit(function (event) {
        event.preventDefault();

        const formData = validateAndGetFormData();
        if (formData) {
            submitFormData(formData);
        }
    });
});
// Проверка данных формы
function validateAndGetFormData() {
    const name = $('#name').val();
    const color = $('#color').val();
    const isAdult = $('#18-years').is(':checked');
    const daytime = $('input[name="daytime"]:checked').val();

    if (!name || !color || !daytime) {
        alert('Please fill in all the required fields.');
        return null;
    }

    return { name, color, isAdult, daytime };
}
// Отправка данных на сервер
function submitFormData(formData) {
    $.ajax({
        url: '/submit',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function () {
            // Отображение данных
            const resultHtml = `
                <h2>Form Submitted Successfully</h2>
                <p>Name: ${formData.name}</p>
                <p>Color: ${formData.color}</p>
                <p>Adult: ${formData.isAdult ? 'Yes' : 'No'}</p>
                <p>Time of Day: ${formData.daytime}</p>
            `;
            $('#result').html(resultHtml);
        },
        error: function () {
            $('#result').html('<h2>Submission Error</h2>');
        }
    });
}
/*

$(document).ready(function () {
    $('#userForm').submit(function (event) {
        event.preventDefault();

        const formData = {
            name: $('#name').val(),
            color: $('#color').val(),
            isAdult: $('#18-years').is(':checked'),
            daytime: $('input[name="daytime"]:checked').val(),
        };

        $.ajax({
            url: '/submit',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                $('#result').html('<h2>Form Submitted</h2><p>' + response + '</p>');
            },
            error: function (xhr) {
                $('#result').html('<h2>Error</h2><p>' + xhr.responseText + '</p>');
            }

        });
    });
});
*/
