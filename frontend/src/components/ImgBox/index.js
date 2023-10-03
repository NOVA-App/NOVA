import React from "react";
import { Dimensions } from "react-native";
import * as S from "./style";
import BasicIcon from "../../assets/ProfileIcon.png";
import API_URL from "../../../config";
const { width, height } = Dimensions.get("window");

const ImgBox = (props) => {
  return (
    <S.ImgContainer
      source={
        props.ProfileUrl
          ? { uri: `${API_URL}/profiles/${props.ProfileUrl}` }
          : BasicIcon
      }
      width={width}
    />
  );
};

export default ImgBox;
