import { ApiProperty } from '@nestjs/swagger';

export class GetFileDto {
  @ApiProperty()
  hardwareKey: string;
  @ApiProperty()
  fileId: number;
  @ApiProperty()
  fileType: string;
  @ApiProperty()
  chunkOffset: number;
  @ApiProperty()
  chuckSize: number;
}
