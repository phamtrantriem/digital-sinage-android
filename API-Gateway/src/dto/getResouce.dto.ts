import { ApiProperty } from '@nestjs/swagger';

export class GetResourceDto {
  @ApiProperty()
  hardwareKey: string;
  @ApiProperty()
  layoutId: number;
  @ApiProperty()
  regionId: string;
  @ApiProperty()
  mediaId: string;
}
