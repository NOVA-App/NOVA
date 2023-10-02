// MyRecoilState.js
// 나이, 게임ID, 여유자금

import { atom } from "recoil";

export const countState = atom({
  key: "countState",
  default: 0,
});

export const isChildBirthState = atom({
  key: "isChildBirthState",
  default: false,
});

export const tokenState = atom({
  key: "tokenState",
  default:
    "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsImV4cCI6MTY5NjYzMDM5MX0.zWtHdQr_XzF735p0nls2_zMOWTRVZU_qq1R0afPYHTQ",
});

export const gameIdState = atom({
  key: "gameIdState",
  default: 0,
});

export const gameDataState = atom({
  key: "gameDataState",
  default: {
    gameId: 0,
    gender: "",
    currentAge: 0,
    isMarried: false,
    numOfChild: 0,
    annualAssets: {
      usableAsset: 0,
      livingCost: 0,
      fixedCost: {
        totalFixedCost: 0,
        monthlyRentCost: 0,
        IRPCost: 0,
        childCost: 0,
        loansCost: 0,
        installmentSavingCost: 0,
      },
    },
    myAssets: {
      totalAsset: 0,
      stocksAsset: 0,
      realtyAsset: 0,
      savingAsset: 0,
      loanAsset: 0,
    },
  },
});
