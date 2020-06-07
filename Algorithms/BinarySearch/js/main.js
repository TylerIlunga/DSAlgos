const binarySearch = (array, key) => {
  let middleIndex = Math.floor(array.length / 2);
  let middleValue = array[middleIndex];

  if (middleValue === key) {
    return true;
  } else if (middleValue < key && array.length > 1) {
    return binarySearch(array.splice(middleIndex, array.length), key);
  } else if (middleValue > key && array.length > 1) {
    return binarySearch(array.splice(0, middleIndex), key);
  } else {
    return false;
  }
}

let testArray = [5, 7, 12, 16, 36, 39, 42, 56, 71]

let result = binarySearch(testArray, 56);
console.log(result);
