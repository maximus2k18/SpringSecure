const dobInput = document.getElementById('dob');
dobInput.addEventListener('input', function(e) {
      // Удаляем все символы, кроме цифр
      let value = e.target.value.replace(/\D/g, '');

  // Добавляем точки автоматически
  if (value.length > 2) {
    value = value.substring(0, 2) + '.' + value.substring(2);
  }
  if (value.length > 5) {
    value = value.substring(0, 5) + '.' + value.substring(5);
  }

  // Ограничение длины до 10 символов (dd.MM.yyyy)
  e.target.value = value.substring(0, 10);
});

// Запрещаем ввод любых символов, кроме цифр
dobInput.addEventListener('keydown', function(e) {
  const allowedKeys = ['Backspace', 'Tab', 'ArrowLeft', 'ArrowRight'];
  if (!allowedKeys.includes(e.key) && !/\d/.test(e.key)) {
    e.preventDefault();
  }
});