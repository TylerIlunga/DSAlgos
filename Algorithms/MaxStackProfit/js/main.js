//O(n) => Linear Runtime
const maxStockProfit = (pricesArray) => {
  let maxProfit = -1;
  let buyPrice = 0;
  let sellPrice = 0;
  let changeBuyPrice = true;

  for (let i = 0; i < pricesArray.length; i++) {
    if (changeBuyPrice) buyPrice = pricesArray[i];
    sellPrice = pricesArray[i + 1];

    if (sellPrice < buyPrice) {
      changeBuyPrice = true;
    } else {
      let tempProfit = sellPrice - buyPrice;
      if (tempProfit > maxProfit) maxProfit = tempProfit;
      changeBuyPrice = false;
    }
  }

  return maxProfit;
}

let stockPrices = [54.68, 53.47, 54.42, 55.70, 55.82, 55.05, 54.84];
let output = maxStockProfit(stockPrices);
console.log(output);
