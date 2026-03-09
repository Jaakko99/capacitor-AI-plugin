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

  async recolorImage(options: { path: string; color: string }): Promise<{ recoloredImagePath: string }> {
         console.log('WEB recolor fallback', options);
         return { recoloredImagePath: options.path }; // just pass back path in web
     }

 /* This will use some sort own method, to connect to mobile service
  async response(color: {value: string}):
  Promise<{value: string )}> {
    console.log("Color hex", color);
    return color;
   */
}
