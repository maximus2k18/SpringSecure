let a = confirm("Введите первое число:");
let b = confirm("Введите второе число:");
function min(a, b) {
  return a < b ? a : b;
}
alert("Минимальное число: " + min(a, b));