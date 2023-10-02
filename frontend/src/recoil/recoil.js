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

export const accessTokenState = atom({
  key: "accessTokenState",
  default: "initialAccessToken",
});

export const refreshTokenState = atom({
  key: "refreshTokenState",
  default: "initialRefreshToken",
});

export const gameIdState = atom({
  key: "gameIdState",
  default: 1,
});