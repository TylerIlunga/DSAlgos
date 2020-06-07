const bubbleSort = (array) => {
  for (var i = array.length; i > 0; i--) {
    for (var j = 0; j < array.length; j++) {
      if (array[j] > array[j+1]) {
        let temp = array[j];
        array[j] = array[j+1];
        array[j+1] = temp;
      }
    }
  }
  return array;
}

let array = [100, 90, 8, 67, 34, 230];
let output = bubbleSort(array);
console.log(output);
