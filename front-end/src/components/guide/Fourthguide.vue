<template>
  <section class="images">
    <img
      v-scrollanimation
      src="https://lh3.googleusercontent.com/proxy/kx-mn0RB1s5bPxJNVFUcJF1ArVNUdR7JmQmHth4bK6SH5KnOlzQCjM7QlDsCAP3IBPoVVDiFLuXdv6Q9UE9vGZ0nmsh7VzKssg_EquknBxowT7Pq38-iH-NB4v-b6QbwDXXUQA5FV1hIGVRjHrAq_sSJ8Q"
      alt
    />
    <img
      v-scrollanimation
      src="https://blog.hmgjournal.com/images/contents/article/20160801-Reissue-city-out-01.jpg"
      alt
    />
    <img v-scrollanimation src="https://img.lovepik.com/photo/50059/9602.jpg_wh860.jpg" alt />
    <img
      v-scrollanimation
      src="https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_720,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/ynmnmpav6irluvmq00ix/%EC%98%A4%EC%8A%AC%EB%A1%9C%EB%8F%84%EC%8B%AC%ED%95%98%EC%9D%B4%EB%9D%BC%EC%9D%B4%ED%8A%B8%EC%9B%8C%ED%82%B9%ED%88%AC%EC%96%B4.jpg"
      alt
    />
  </section>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.images {
  position: relative;
  width: 100vw;

  img {
    float: left;
    width: 50%;
    height: auto;
  }
}

.before-enter {
  opacity: 0;
  transform: scale(0.5) rotateZ(-25deg);
  transition: all 1s ease-out;
}

.enter {
  opacity: 1;
  transform: scale(1) rotateZ(0deg);
}
</style>