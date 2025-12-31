import { defineStore } from 'pinia'

export const usePlayerStore = defineStore('player', {
  state: () => ({
    playlist: [],        // 当前播放列表（歌单 / 搜索结果 / 推荐列表）
    currentIndex: -1,    // 当前播放索引
    currentSong: null,   // 当前播放歌曲
    isPlaying: false
  }),

  actions: {
    /** 播放一首歌（可来自任意列表） */
    play(song, list = []) {
      // 如果传了新列表，更新播放列表
      if (list.length > 0) {
        this.playlist = list
        this.currentIndex = list.findIndex(item => item.id === song.id)
      } else {
        // 单曲播放（不来自列表）
        this.playlist = [song]
        this.currentIndex = 0
      }

      this.currentSong = song
      this.isPlaying = true
    },

    /** 上一首 */
    prev() {
      if (this.playlist.length === 0) return

      this.currentIndex =
        this.currentIndex <= 0
          ? this.playlist.length - 1
          : this.currentIndex - 1

      this.currentSong = this.playlist[this.currentIndex]
      this.isPlaying = true
    },

    /** 下一首 */
    next() {
      if (this.playlist.length === 0) return

      this.currentIndex =
        this.currentIndex >= this.playlist.length - 1
          ? 0
          : this.currentIndex + 1

      this.currentSong = this.playlist[this.currentIndex]
      this.isPlaying = true
    },

    togglePlay() {
      this.isPlaying = !this.isPlaying
    }
  }
})
