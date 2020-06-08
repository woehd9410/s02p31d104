<template>
  <div class="firstpage">
    <div class="bgimg w3-display-container w3-animate-opacity w3-text-white">
      <div class="w3-display-topleft w3-padding-large w3-xlarge">Logo</div>
      <div class="w3-display-middle">
        <div class="animation orbit replay">
          <h1 class="w3-jumbo w3-animate-top">Helpromise</h1>
          <hr class="w3-border-grey" style="margin:auto;width:40%" />
          <p class="w3-large w3-center">team helper</p>
        </div>
      </div>
      <div class="w3-display-bottommiddle w3-padding-large">
        <!-- 버튼 타입1 -->
        <p>
          <button class="w3-button w3-black">시작하기</button>
        </p>
        <!-- 버튼 타입2 -->
        <p>
          <button
            class="w3-button w3-white w3-padding-large w3-large w3-opacity w3-hover-opacity-off"
          >시작하기</button>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.firstpage {
  position: relative;
  min-height: 80vh;
  width: 100vw;
  .bgimg {
    position: relative;
    width: 100vw;
    background-image: url("https://data.photo-ac.com/data/thumbnails/e4/e41a516cd4ac0f1a79cf0e47e6397da4_t.jpeg");
    min-height: 80vh;
    background-position: center;
    background-size: cover;

    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
      font-family: "Lato", sans-serif;
      font-size: 1.5em;
    }
  }
}
</style>