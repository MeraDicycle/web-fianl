import { defineStore } from 'pinia'

export const usePlayerStore = defineStore('player', {
  state: () => ({
    currentSong: null, // 当前播放歌曲
    isPlaying: false
  }),

  actions: {
    play(song) {
      this.currentSong = song
      this.isPlaying = true
    },
    togglePlay() {
      this.isPlaying = !this.isPlaying
    }
  }
})
