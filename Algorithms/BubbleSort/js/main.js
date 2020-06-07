const bubbleSort = (array) => {
  for(let i = array.length; i > 0; i--) {
    for(let j = 0; j < i; j++) {
      if(array[j] > array[j + 1]) {
        let temp = array[j];
        array[j] = array[j + 1];
        array[j + 1] = temp;
      }
    }
  }
  return array;
}


let arrayOfNumbers = [1, 5, 6, 7, 10 , 2, 8, 20];
let output = bubbleSort(arrayOfNumbers);
console.log(output);
