import { get as getDictDetail } from '@/api/system/dictDetail'

export default class Dict {
  constructor(dict) {
    this.dict = dict
  }

  async init(names, completeCallback) {
    if (names === undefined || name === null) {
      throw new Error('need Dict names')
    }
    const ps = []
    names.forEach(n => {
      this.dict.dict[n] = {}
      this.dict.label[n] = {}
      this.dict[n] = []
      ps.push(getDictDetail(n).then(data => {
        this.dict[n].splice(0, 0, ...data.content)
        data.content.forEach(d => {
          if (parseInt(d.value).toString() !== 'NaN') {
            d.value = parseInt(d.value)
          }
          this.dict.dict[n][d.value] = d
          this.dict.label[n][d.value] = d.label
        })
      }))
    })
    await Promise.all(ps)
    completeCallback()
  }
}
