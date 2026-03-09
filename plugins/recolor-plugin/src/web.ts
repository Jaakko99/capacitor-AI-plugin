import { WebPlugin } from '@capacitor/core';

import type { AIRecolor } from './definitions';

export class AIRecolorWeb extends WebPlugin implements AIRecolor {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

 async response(color: {value: string}): Promise<{value_ string}> {
        console.log("Color hex", color)
        return color;
     }

 /* This will use some sort own method, to connect to mobile service
  async response(color: {value: string}):
  Promise<{value: string )}> {
    console.log("Color hex", color);
    return color;
   */
}
