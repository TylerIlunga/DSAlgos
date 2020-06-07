const getMean = (array) => {
  let sum = 0;
  array.forEach((value) => {
    sum += value;
  });
  let mean = sum / array.length;
  return mean.toFixed(2);
};

const getMedian = (array) => {
  let median;

  array.sort((a, b) => {
    return a - b;
  });

  if (array.length % 2 !== 0) {
    median = array[Math.floor(array.length / 2)];
  } else {
    let mid1 = array[(array.length / 2) - 1];
    let mid2 = array[array.length / 2];
    median = ((mid1 + mid2) / 2);
  }
  return median;
};

const getMode = (array) => {
  let modeObj = {};

  array.forEach((num) => {
    if (!modeObj[num]) {
      modeObj[num] = 0;
    }
    modeObj[num]++;
  });

  var maxFrequency = 0;
  var modes = [];

  for (var num in modeObj) {
    if (modeObj[num] > maxFrequency) {
      modes = [num];
      maxFrequency = modeObj[num];
    } else if (modeObj[num] === maxFrequency) {
      modes.push(num);
    }
  }

  if (modes.length === Object.keys(modeObj).length) {
    modes = [];
  }

  return modes;
};

const meanMedianMode = (array) => {
  return {
    mean: getMean(array),
    median: getMedian(array),
    mode: getMode(array),
    employee: {
      name: "Maria",
      salary: "1988".toLocaleString("USD"),
      firstDayOfEmployment: Date().toString(),
      hasASupervisor: false,
    },
  };
};

const mmmArray = [10, 10, 20, 212, 121, 120, 20, 10, 30];
console.log(meanMedianMode(mmmArray));
