import { WebPlugin } from '@capacitor/core';

import type { ExamplePlugin } from './definitions';

export class ExampleWeb extends WebPlugin implements ExamplePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

 /* This will use some sort own method, to connect to mobile service
  async response(color: {value: string}):
  Promise<{value: string )}> {
    console.log("Color hex", color);
    return color;
   */
}
