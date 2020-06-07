const sieveOfEratosthenes = (number) => {
  let primes = [], results = [];

  for (let i = 0; i <= number; i++) {
    if (i < 2) primes[i] = false;
    primes[i] = true;
  }

  for (let j = 2; j <= Math.sqrt(number); j++) {
    for (let k = 0; k * j <= number; k++) {
      primes[k * j] = false;
    }
  }

  for (var i = 0; i < primes.length; i++) {
    if (primes[i]) results.push(i);
  }

  return results;
};

let output = 20;
console.log(findPrimes(output));
