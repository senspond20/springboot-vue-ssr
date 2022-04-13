<template>
  <section>
    <h2>This is Home page</h2>
    <div>{{ word }}</div>
  </section>
</template>

<script>
export default{
  data() {
    return {
      pageId: 'HomePage'
    }
  },
  computed: {
    word() {
      return this.$store.getters.getWord
    }
  },
  mounted() {
    if (window.__INITIAL_STATE__ === null) {
      this.getWord()
    } else [
      window.__INITIAL_STATE__ = null
    ]
  },
  methods: {
    getWord() {
      this.$store.dispatch('getWordAction', this.pageId)
    }
  }
}
</script>

<style scoped>
div > div:nth-child(1) {
  height: 20px;
}
</style>