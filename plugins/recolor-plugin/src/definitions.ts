export interface AIRecolor {
  echo(options: { value: string }): Promise<{ value: string }>;
  recolorImage(options: {path: string; color: string}): Promise<{recoloredImagePath: string}>;
}
