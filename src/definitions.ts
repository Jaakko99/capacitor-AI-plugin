export interface RecolorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
