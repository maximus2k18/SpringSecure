function togglePassword() {
    const passwordField = document.getElementById('password-strength');
    const eyeIcon = document.getElementById('eye-icon');

    console.log('togglePassword вызвана');  // Лог для проверки

    if (eyeIcon) {
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            eyeIcon.src = '/images/eye-open-icon.png';
            eyeIcon.alt = 'Скрыть пароль';
        } else {
            passwordField.type = 'password';
            eyeIcon.src = '/images/eye-closed-icon.png';
            eyeIcon.alt = 'Показать пароль';
        }
    } else {
        console.error('Элемент с id="eye-icon" не найден.');
    }
}

// Привязываем обработчик события для кнопки
document.addEventListener('DOMContentLoaded', function() {
    const toggleButton = document.querySelector('.field-type__toggler');
    if (toggleButton) {
        toggleButton.addEventListener('click', togglePassword);
    } else {
        console.error('Кнопка с классом .field-type__toggler не найдена.');
    }
});
