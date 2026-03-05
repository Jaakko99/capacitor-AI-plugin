export interface AIRecolor {
  echo(options: { value: string }): Promise<{ value: string }>;
}
