import { WebPlugin } from '@capacitor/core';

import type { RecolorPlugin } from './definitions';

export class RecolorWeb extends WebPlugin implements RecolorPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
