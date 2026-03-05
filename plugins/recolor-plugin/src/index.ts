import { registerPlugin } from '@capacitor/core';

import type { AIRecolor } from './definitions';

const pluginRes = registerPlugin<AIRecolor>('pluginRes', {
  web: () => import('./web').then((m) => new m.ExampleWeb()),
});

export * from './definitions';
export { pluginRes };
