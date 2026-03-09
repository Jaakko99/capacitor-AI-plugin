import { registerPlugin } from '@capacitor/core';

import type { AIRecolor } from './definitions';

const AIRecolorPlugin  = registerPlugin<AIRecolor>('pluginRes', {
  web: () => import('./web').then((m) => new m.AIRecolorWeb()),
});

export * from './definitions';
export { AIRecolorPlugin  };
