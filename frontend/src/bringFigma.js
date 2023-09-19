const axios = require('axios');
const fs = require('fs/promises'); // Node.js의 파일 시스템 모듈 사용

// Figma 파일 및 스타일 정보 설정
const fileKey = 'YOUR_FIGMA_FILE_KEY'; // Figma 파일의 고유 키
const personalAccessToken = 'YOUR_FIGMA_PERSONAL_ACCESS_TOKEN'; // Figma API 토큰
const stylesFilePath = 'colorSelector.css'; // 저장할 CSS 파일 경로

// Figma API를 통해 스타일 정보를 가져오는 함수
async function fetchFigmaStyles() {
  const response = await axios.get(`https://api.figma.com/v1/files/${fileKey}/styles`, {
    headers: {
      'X-Figma-Token': personalAccessToken,
    },
  });

  const styles = response.data.meta.styles;
  let cssContent = ':root {\n';

  for (const styleId in styles) {
    const style = styles[styleId];
    const variableName = `--${style.name.replace(/\s+/g, '-')}`;
    const color = style.fills[0].color;

    cssContent += `  ${variableName}: ${color};\n`;
  }

  cssContent += '}\n';

  return cssContent;
}

// CSS 스타일 정보를 파일에 저장하는 함수
async function saveStylesToFile(cssContent) {
  await fs.writeFile(stylesFilePath, cssContent);
  console.log(`스타일 정보를 ${stylesFilePath} 파일에 저장했습니다.`);
}

// 메인 함수: Figma 스타일 정보 가져오고 파일에 저장
async function main() {
  try {
    const cssContent = await fetchFigmaStyles();
    await saveStylesToFile(cssContent);
  } catch (error) {
    console.error('Error:', error);
  }
}

main();
