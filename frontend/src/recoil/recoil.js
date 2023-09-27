// MyRecoilState.js
// 나이, 게임ID, 여유자금

import { atom } from "recoil";

export const countState = atom({
  key: "countState",
  default: 0,
});
