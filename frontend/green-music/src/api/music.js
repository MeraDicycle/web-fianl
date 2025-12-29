import request from './request'

export function getRecommendSongs() {
  return request.get('/music/recommend')
}
