function checkPasswordStrength() {
    const password = document.getElementById('password-strength').value;
    const strengthBar = document.getElementById('password-strength-bar');
    const strengthText = document.getElementById('password-strength-text');

    let strength = 0;

    // Проверка длины пароля
    if (password.length >= 6) strength++;

    // Проверка наличия цифры
    if (/\d/.test(password)) strength++;

    // Проверка наличия буквы
    if (/[a-zA-Z]/.test(password)) strength++;

    // Проверка наличия специального символа
    if (/[\W_]/.test(password)) strength++;

    // Обновление полоски и текста
    if (password.length < 6) {
        strengthBar.style.backgroundColor = 'red';
        strengthText.textContent = 'Пароль слишком короткий';
    } else if (strength === 4) {
        strengthBar.style.backgroundColor = 'green';
        strengthText.textContent = 'Надежный пароль';
    } else {
        strengthBar.style.backgroundColor = 'orange';
        strengthText.textContent = 'Простой пароль';
    }
}