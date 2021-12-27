export interface NavItem {
  displayName: string,
  matIcon: string,
  fullPath: string,
  ariaLabel: string
  action(): void
}
