import React, { useState } from 'react';
import { Ionicons } from '@expo/vector-icons';
import {
  PageContainer,
  ToggleButton,
  TextContainer,
  DescriptionText,
} from './style';

function Toggle({ description, isExpanded, toggleSwitch }) {
  return (
    <ToggleButton onPress={toggleSwitch}>
      <Ionicons
        name={isExpanded ? 'chevron-up' : 'chevron-down'}
        size={24}
        color="black"
      />
      <TextContainer>
        <DescriptionText numberOfLines={isExpanded ? null : 1}>
          {description}
        </DescriptionText>
      </TextContainer>
    </ToggleButton>
  );
}

function NewsPage() {
  const [isExpanded, setIsExpanded] = useState([false, false, false, false, false]);

  const toggleSwitch = (index) => {
    setIsExpanded((prevStates) => {
      const newState = [...prevStates];
      newState[index] = !newState[index];
      return newState;
    });
  };

  const descriptions = [
    '토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 ',
    '토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 ',
    '토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 ',
    '토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 ',
    '토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 토글 ',
  ];

  return (
    <PageContainer>
      {descriptions.map((description, index) => (
        <Toggle
          key={index}
          description={description}
          isExpanded={isExpanded[index]}
          toggleSwitch={() => toggleSwitch(index)}
        />
      ))}
    </PageContainer>
  );
}

export default NewsPage;
