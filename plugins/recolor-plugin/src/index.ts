import { registerPlugin } from '@capacitor/core';
import type { AIRecolor } from './definitions';
import { AIRecolorWeb } from './web'

const AIRecolorPlugin = registerPlugin<AIRecolor>('AIRecolor', {
  web: () => new AIRecolorWeb(), // fallback for web
});

export * from './definitions';
export { AIRecolorPlugin };
