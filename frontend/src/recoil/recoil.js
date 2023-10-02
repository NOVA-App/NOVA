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
