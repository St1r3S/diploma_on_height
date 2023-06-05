const dropdown = document.getElementById("product");
const requestedQty = document.getElementById("quantity");
const changeTotalPrice = function () {
    const selectedOption = dropdown.options[dropdown.selectedIndex];
    const selectedCurrency = selectedOption.getAttribute('data-currency');
    const selectedUnitPrice = selectedOption.getAttribute('data-unitPrice');

    const totalPrice = document.getElementById("totalPrice");
    const temp = requestedQty.value * selectedUnitPrice;
    totalPrice.value = temp + selectedCurrency;
}
changeTotalPrice();
dropdown.addEventListener("change", changeTotalPrice);
requestedQty.addEventListener("change", changeTotalPrice);