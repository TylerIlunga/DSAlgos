const reverseArrayInPlace = (array) => {
  for (var i = 0; i < array.length / 2; i++) {
    let tempVar = array[i];
    array[i] = array[array.length - 1 - i];
    array[array.length - 1 - i] = tempVar;
  }

  return array;
}

console.log(reverseArrayInPlace([1, 23, 234, 353]));
