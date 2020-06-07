const reverseArray = (array) => {
  for (let i = 0; i < array.length / 2; i++) {
    let temporaryValue = array[i];
    array[i] = array[array.length - 1 - i];
    array[array.length - 1 - i] = temporaryValue;
  }
  return array;
};

let a = [1, 232, 2423, 2343, 543345, 23423],
  result = reverseArray(a);

console.log(result);
