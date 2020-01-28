import { Role } from './Role';

export interface User {
  username: string;
  active: boolean;
  roles: Array<Role>;
}
